Grouping WebElements by id attribute which has a defined format with partially changing part, and sorting the id values.  
============

This is a [Katalon Studio](https://www.katalon.com/) project for demonstration purpose.
You can download the zip file from the [releases](https://github.com/kazurayam/KatalonDiscussion7527/releases) page to your PC, and run it with your Katalon Studio.

This project was developed with Katalon Studio version 5.7.1.

This project was developed in order to propse a solution to a question raised by a comment to the following discussion 
in the Katalon Forum.

- https://forum.katalon.com/discussion/7527/how-can-i-handle-dynamic-web-element-id-and-store-it-in-a-variable

Especially, Hamza Abbas raised a question at 11/14/2018

- https://forum.katalon.com/discussion/comment/25677#Comment_25677

Hamza's question was as follows:

>This is the html of object i have captured
>`<span class="select2-selection__rendered" id="select2-bot_task_action_158-container" role="textbox" aria-readonly="true" title="Email">Email</span>`
>But when this object is created again by katalon the id changes to
>`<span class="select2-selection__rendered" id="select2-bot_task_action_159-container" role="textbox" aria-readonly="true" title="Email">Email</span>`
>The xpath stored in object repository is
>`//*[@id="select2-bot_task_action_158-container"]`
>But when katalon actually tries to click the object 
>xpath has become
>`//*[@id ="select2-bot_task_action_159-container"]`
>what should i do?

Hamza wanted to identify a span element which was last inserted into the target HTML.

# Problem to solve

The target HTML contains multiple `<span>` elements which are almost identical each other except the id attribute value.

The `<span>` elements are inserted or deleted by interactive operation to the page.

The `<span>`'s id attribute value has a fixed format:

1. it starts with a fixed string 'select2-bot_task_action_'
2. which is followed by a digit
3. which is followed by a fixed string '-container'

# What is assumed

I assume that the digit in the id attribute value is allocated when the `<span>` element is inserted in the HTML,
and the digit will be incremented: the new digit will be equal to the last value + 1.

The digit will simply become bigger and bigger. For example, 158,159, ..., 999, 1000, ..., 9999, ..., 99999, ...

The digit does not goes back to some initial value. For example, ..., 998, 999, 0, 1, 2, ...

I am aware this assumption is too naive and likely to break.
But here I assume this because I am not informed of anymore about Hamza's Appliation Under Test.



# Solution

By calling [`List<WebElement> com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords.findWebElements(TestObject, int)`](https://api-docs.katalon.com/studio/v4.5.0/api/com.kms.katalon.core.webui/com/kms/katalon/core/webui/keyword/WebUiBuiltInKeywords.html), I can get a list of `org.openqa.selenium.WebElement`.

I would use XPath: `//span[starts-with(@id, 'select2-bot_task_action')]` in order to select the span elements in question.

I would collect the values of id attribute of the selected span elements, parse the id attribute values, sort the list of id values by the descending order of hte contained digits.

I would identify the id value with the greatest digit. It should be the one Hamza wanted.

# Description

1. I made a mock target HTML at http://demoaut-mimic.kazurayam.com/testbed_7527.html.
2. The target HTML has code fragrement as follows:
```
<table class="table table-bordered">
  <thead>
  </thead>
  <tbody>
    <tr>
      <td><span class="select2-selection__rendered" id="select2-bot_task_action_158-container" role="textbox" aria-readonly="true" title="Email">Email</span></td>
    </tr>
    <tr>
      <td><span class="select2-selection__rendered" id="select2-bot_task_action_159-container" role="textbox" aria-readonly="true" title="Email">Email</span></td>
    </tr>
    <tr>
      <td><span class="select2-selection__rendered" id="select2-bot_task_action_160-container" role="textbox" aria-readonly="true" title="Email">Email</span></td>
    </tr>
  </tbody>
</table>
```
3. I made a TestObject named `Object Repository/Page_Katalon Discussion 7527/span_Email`. It uses the following XPath expression to select a group of span elements:
```
//span[starts-with(@id,"select2-bot_task_action_")]
```
4. I have developed a Test Case named `Test Cases/TC1`. The source code is [here](Scripts/TC1/Script1542283273137.groovy). It is a bit complicated Groovy script. Hamza, please study this. Your problem is difficult enough and deserves this level of programming skill.
5. When I run the Test Case, I got the following ouput in the log.
```
11-16-2018 08:08:40 PM - [INFO]   - before sorting:
...
11-16-2018 08:08:40 PM - [INFO]   - >>> id is select2-bot_task_action_158-container, id.getSequenceNumber() is 158
...
11-16-2018 08:08:40 PM - [INFO]   - >>> id is select2-bot_task_action_159-container, id.getSequenceNumber() is 159
...
11-16-2018 08:08:40 PM - [INFO]   - >>> id is select2-bot_task_action_160-container, id.getSequenceNumber() is 160
...
11-16-2018 08:08:40 PM - [INFO]   - after sorting:
...
11-16-2018 08:08:40 PM - [INFO]   - <<< id is select2-bot_task_action_160-container, id.getSequenceNumber() is 160
...
11-16-2018 08:08:40 PM - [INFO]   - <<< id is select2-bot_task_action_159-container, id.getSequenceNumber() is 159
...
11-16-2018 08:08:40 PM - [INFO]   - <<< id is select2-bot_task_action_158-container, id.getSequenceNumber() is 158
...
11-16-2018 08:08:40 PM - [INFO]   - >>> the latest one is select2-bot_task_action_160-container
```

I do not know full HTML code of Hamza's AUT, so that my test case and my test object as example may not be applicable to his case.

Hamza, please modify my sample code and solve your problem for yourself.







