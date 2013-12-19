<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:url var="knockoutJsUrl" value="/resources/js/knockout-3.0.0.js" />
<spring:url var="activeJsUrl" value="/resources/js/activeLink/userExam.js" />
<spring:url var="getClosedQuestionUrl" value="/student/userExam/{id}/closedQuestion" >
    <spring:param name="id" value="${userExamId}"/>
</spring:url>

<!DOCTYPE html>
<html>
    <head>
        <title><spring:message code="userExam.sheet.title" arguments="${testTemplateSubject}, ${testTemplateSuffix}"/></title>
    </head>
    <body>
        <div class="page-header">
            <h1>
                <spring:message code="userExam.sheet.header" arguments="${testTemplateSubject}, ${testTemplateSuffix}"/>
            </h1>
        </div>

        <div class="row">
            <div class="col-lg-3">
                <div class="list-group">
                    <ul class="list-group" data-bind="foreach: questionHeaders">
                        <li data-bind="text: $root.questionHeaderTitle(orderNumber),
                            value: id,
                            css: { active: id == $root.chosenQuestionHeaderId() },
                            click: $root.loadQuestion" class="list-group-item">
                            Pytanie
                        </li>
                    </ul>
                </div>
            </div>
            <div class="col-lg-9" data-bind="with: chosenQuestionData">
                <h3 data-bind="text: question, value: id"></h3><br>

                <div data-bind="foreach: userExamClosedAnswers">
                        <div class="row" >
                            <div class="col-md-1">
                                <button type="button" class="btn btn-default" data-bind="click: negateAnswer">
                                    <span class="glyphicon" data-bind="css: markedOrNot"></span>
                                </button>                            
                            </div>
                            <div class="col-md-8" data-bind="text: answer, value: id, click: negateAnswer">
                            </div>
                        </div>
                    <br>
                </div>
                <br>
                <button type="button" class="btn btn-success btn-lg btn-block" data-bind="click: $parent.sendAnswer">
                    <spring:message code="button_submit"/>
                </button>
            </div>
        </div>
        <script src="${knockoutJsUrl}"></script>
        <script>
            function Answer(id, answer, correct, imageId) {
                var self = this;
                self.id = id;
                self.answer = answer;
                self.correct = ko.observable(correct);
                self.imageId = imageId;

                self.markedOrNot = ko.computed(function() {
                    return (self.correct() ? "glyphicon-check" : "glyphicon-unchecked");
                }, self);
                self.negateAnswer = function() {
                    self.correct(!self.correct());
                };
            }

            function Question() {
                var self = this;
                self.id = ko.observable();
                self.question = ko.observable();
                self.imageId = ko.observable();
                self.userExamClosedAnswers = ko.observableArray();

                self.init = function(data) {
                    self.id(data.id);
                    self.question(data.question);
                    self.userExamClosedAnswers.removeAll();
                    for (var i = 0; i < data.userExamClosedAnswers.length; i++) {
                        var closedAnswer = data.userExamClosedAnswers[i];
                        self.userExamClosedAnswers.push(new Answer(closedAnswer.id,
                                closedAnswer.answer,
                                closedAnswer.correct,
                                closedAnswer.imageId));
                    }
                };
            }

            function WebmailViewModel() {
                // Data
                var self = this;

                self.questionHeaders = ${questionHeaders};
                self.chosenQuestionHeaderId = ko.observable();
                self.chosenQuestionData = new Question();

                // Behaviours    
                self.loadQuestion = function(questionHeader) {
                    $.getJSON('${getClosedQuestionUrl}',
                            {closedQuestionId: questionHeader.id},
                    function(data) {
                        self.chosenQuestionData.init(data);
                    });
                    self.chosenQuestionHeaderId(questionHeader.id);
                };

                self.subbmitQuestion = function(chosenQuestionData) {

                };
                self.questionHeaderTitle = function(questionHeaderOrderNumber) {
                    return '<spring:message code="userExam.questionList.text"/> ' + questionHeaderOrderNumber;
                };
            }
            ;


            ko.applyBindings(new WebmailViewModel());
        </script>
        <script src="${activeJsUrl}"></script>
    </body>
</html>
