<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:url var="lightboxCssUrl" value="/resources/css/lightbox.css" />
<spring:url var="ajaxLoaderUrl" value="/resources/images/ajax-loader.gif" />
<spring:url var="knockoutJsUrl" value="/resources/js/knockout-3.0.0.js" />
<spring:url var="momentJsUrl" value="/resources/js/moment-with-langs.min.js" />
<spring:url var="lightboxJsUrl" value="/resources/js/lightbox-2.6.min.js" />
<spring:url var="activeJsUrl" value="/resources/js/activeLink/userExam.js" />
<spring:url var="getClosedQuestionUrl" value="/student/userExam/{id}/closedQuestion" >
    <spring:param name="id" value="${userExamId}"/>
</spring:url>
<spring:url var="imageWithoutIdUrl" value="/image/"/>

<!DOCTYPE html>
<html>
    <head>
        <title><spring:message code="userExam.sheet.title" arguments="${testTemplateSubject}, ${testTemplateSuffix}"/></title>
        <link type="text/css" href="${lightboxCssUrl}" rel="stylesheet">
    </head>
    <body>
        <div class="page-header">
            <h1>
                <spring:message code="userExam.sheet.header" arguments="${testTemplateSubject}, ${testTemplateSuffix}"/>
                <span id="ajaxLoader">
                    <img  src="${ajaxLoaderUrl}"/>
                    <small><spring:message code="global.ajax.text"/></small>
                </span>
            </h1>
        </div>
        <div class="row">
            <div class="col-lg-3">
                <div class="alert alert-warning text-center">
                    <spring:message code="userExam.inactiveInSeconds.text"/><br>
                    <strong id="counter">--:--:--</strong>
                </div>
                <div class="alert alert-success text-center" data-bind="visible: questionHeaders().length === 0">
                    <spring:message code="userExam.allQuestionsAreSubbmitted.text"/><br>
                </div>
                <div class="list-group" data-bind="template: {foreach: questionHeaders, beforeRemove: hideQuestionHeader}">
                    <a class="list-group-item" data-bind="text: $root.questionHeaderTitle(orderNumber),
                        value: id,
                        css: { active: id == $root.chosenQuestionHeaderId() },
                        click: $root.loadQuestion">
                        Pytanie
                    </a>
                </div>
            </div>
            <div class="col-lg-9" data-bind="with: chosenQuestionData, fadeVisible: chosenQuestionData.loaded">
                <div class="media">
                    <a class="pull-right thumbnail" data-lightbox="image" data-bind="attr: { href: imageSrc }, visible: imageSrc()">
                        <img width="64px" height="64px" data-bind="attr: { src: imageSrc }" >
                    </a>
                    <div class="media-body">
                        <h4 class="media-heading" data-bind="text: question, value: id"></h4>
                    </div>
                </div>

                <div data-bind="foreach: userExamClosedAnswers">
                    <hr>
                    <div class="row">
                        <div class="col-md-1">
                            <button type="button" class="btn btn-default btn-lg" data-bind="click: negateAnswer">
                                <span class="glyphicon" data-bind="css: markedOrNot"></span>
                            </button>                            
                        </div>
                        <div class="col-md-8 media" data-bind="">
                            <a class="pull-right thumbnail" data-lightbox="images" data-bind="attr: { href: imageSrc }, visible: imageSrc">
                                <img width="64px" height="64px" data-bind="attr: { src: imageSrc }" >
                            </a>
                            <div class="media-body clickable" data-bind="text: answer, value: id, click: negateAnswer">
                            </div>
                        </div>
                    </div>
                </div>
                <hr>
                <button type="button" class="btn btn-success btn-lg" data-bind="click: $parent.subbmitQuestion, visible: loaded, enable: loaded">
                    <spring:message code="button_submit"/>
                </button>
            </div>
        </div>
        <script src="${momentJsUrl}"></script>
        <script>
            var diffTime = ${inactiveInSeconds};
            var duration = moment.duration(diffTime, 'seconds');
            var interval = 1;
            function formatter(numericValue) {
                var result = numericValue;
                if (numericValue < 10) {
                    result = "0" + result;
                }
                return result;
            }
            setInterval(function() {
                duration = moment.duration(duration.asSeconds() - interval, 'seconds');
                if (duration.asSeconds() < 0) {
                    location.reload();
                }
                $('#counter').text(formatter(duration.hours()) + ':'
                        + formatter(duration.minutes()) + ':'
                        + formatter(duration.seconds()));
            }, 1000);
        </script>
        <script src="${lightboxJsUrl}"></script>
        <script src="${knockoutJsUrl}"></script>
        <script>
            function Answer(id, answer, correct, imageId) {
                var self = this;
                self.id = id;
                self.answer = answer;
                self.correct = ko.observable(correct);
                if (imageId) {
                    self.imageSrc = '${imageWithoutIdUrl}' + imageId;
                } else {
                    self.imageSrc = null;
                }

                self.markedOrNot = ko.computed(function() {
                    return (self.correct() ? "glyphicon-check" : "glyphicon-unchecked");
                }, self);
                self.negateAnswer = function() {
                    self.correct(!self.correct());
                };
            }

            function Question() {
                var self = this;
                self.loaded = ko.observable(false);
                self.id = ko.observable();
                self.question = ko.observable();
                self.imageSrc = ko.observable();
                self.userExamClosedAnswers = ko.observableArray();

                self.init = function(data) {
                    self.id(data.id);
                    self.question(data.question);
                    self.imageSrc('${imageWithoutIdUrl}' + data.imageId);
                    if (data.imageId) {
                        self.imageSrc('${imageWithoutIdUrl}' + data.imageId);
                    } else {
                        self.imageSrc(null);
                    }

                    self.userExamClosedAnswers.removeAll();
                    for (var i = 0; i < data.userExamClosedAnswers.length; i++) {
                        var closedAnswer = data.userExamClosedAnswers[i];
                        self.userExamClosedAnswers.push(new Answer(closedAnswer.id,
                                closedAnswer.answer,
                                closedAnswer.correct,
                                closedAnswer.imageId));
                    }
                    self.loaded(true);
                };
                self.destroy = function() {
                    self.loaded(false);
                };
            }

            function WebmailViewModel() {
                // Data
                var self = this;

                self.questionHeaders = ko.observableArray(${questionHeaders});
                self.chosenQuestionHeaderId = ko.observable();
                self.chosenQuestionData = new Question();

                // Behaviours    
                self.loadQuestion = function(questionHeader) {
                    $("#ajaxLoader").show();

                    self.chosenQuestionData.destroy();
                    $.getJSON('${getClosedQuestionUrl}',
                            {closedQuestionId: questionHeader.id},
                    function(data) {
                        self.chosenQuestionData.init(data);
                        $("#ajaxLoader").hide();
                    });
                    self.chosenQuestionHeaderId(questionHeader.id);
                };

                self.subbmitQuestion = function(chosenQuestionData) {
                    var jsonData = ko.toJSON(chosenQuestionData);

                    $.ajax({
                        type: 'POST',
                        url: "${getClosedQuestionUrl}",
                        data: jsonData,
                        contentType: "application/json",
                        beforeSend: function(xhrd) {
//                            alert("beforeSend");

                        },
                        success: function(data) {
                            for (var i = 0; i < self.questionHeaders().length; i++) {
                                var questionHeader = self.questionHeaders()[i];
                                if (questionHeader.id === chosenQuestionData.id()) {
                                    self.chosenQuestionHeaderId(null);
                                    self.chosenQuestionData.destroy();
                                    self.questionHeaders.remove(questionHeader);
                                    break;
                                }
                            }
                        },
                        error: function(xhr, status, error) {
                            alert("error");
                        }
                    });
                };
                self.questionHeaderTitle = function(questionHeaderOrderNumber) {
                    return '<spring:message code="userExam.questionList.text"/> ' + questionHeaderOrderNumber;
                };

                self.hideQuestionHeader = function(elem) {
                    if (elem.nodeType === 1) {
                        $(elem).fadeOut(function() {
                            $(elem).remove();
                        });
                    }
                    ;
                }
                ;
            }

            ko.bindingHandlers.fadeVisible = {
                init: function(element, valueAccessor) {
                    // Initially set the element to be instantly visible/hidden depending on the value
                    var value = valueAccessor();
                    $(element).toggle(ko.unwrap(value)); // Use "unwrapObservable" so we can handle values that may or may not be observable
                },
                update: function(element, valueAccessor) {
                    // Whenever the value subsequently changes, slowly fade the element in or out
                    var value = valueAccessor();
                    ko.unwrap(value) ? $(element).fadeIn() : $(element).fadeOut();
                }
            };

            ko.applyBindings(new WebmailViewModel());
        </script>
        <script src="${activeJsUrl}"></script>
    </body>
</html>
