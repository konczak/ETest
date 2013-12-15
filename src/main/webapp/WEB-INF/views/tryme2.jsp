<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:url var="knockoutJsUrl" value="/resources/js/knockout-3.0.0.js" />
<spring:url var="tryquestionUrl" value="/tryme2/tryQuestion" />

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="${knockoutJsUrl}"></script>
    </head>
    <body>
        <h1>Hello World!</h1>
        <div class="row">
            <div class="col-lg-3">
                <ul class="list-group" data-bind="foreach: folders">
                    <li data-bind="text: $data,
                        css: { active: $data == $root.chosenFolderId() },
                        click: $root.goToFolder" class="list-group-item">
                    </li>
                </ul>
            </div>
            <div class="col-lg-9" data-bind="with: chosenFolderData">
                <h2 data-bind="text: question"></h2>
                <!-- Mails grid -->
                <table class="table" >
                    <thead><tr><th>COrrect</th><th>Id</th><th>Answer</th><th>IMage</th></tr></thead>
                    <tbody data-bind="foreach: answers">
                        <tr>
                            <td><input type="checkbox" data-bind="checked: correct"></td>
                            <td data-bind="text: id"></td>
                            <td data-bind="text: answer"></td>
                            <td data-bind="text: imageId"></td>
                        </tr>     
                    </tbody>
                </table>
                <button data-bind="click: $parent.sendAnswer">Zatwierdz</button>
            </div>
        </div>

        <script>
            function WebmailViewModel() {
                // Data
                var self = this;
                self.folders = ['123', '2', '3', '4', '5'];
                self.chosenFolderId = ko.observable();
                self.chosenFolderData = ko.observable();

                // Behaviours    
                self.goToFolder = function(folder) {
                    self.chosenFolderId(folder);
                    $.get('${tryquestionUrl}', {folder: folder}, self.chosenFolderData);
                };
                self.sendAnswer = function(chosenFolderData) {
                    var jsonData = ko.toJSON(chosenFolderData);

                    //alert(jsonData);
//                    $.post('${tryquestionUrl}', jsonData, function(returnedData) {
//                        // This callback is executed if the post was successful    
//                    });
                    $.ajax({
                        type: 'POST',
                        url: "${tryquestionUrl}",
                        data: jsonData,
                        contentType: "application/json"
                    });
                };
            }
            ;

            ko.applyBindings(new WebmailViewModel());
        </script>
    </body>
</html>
