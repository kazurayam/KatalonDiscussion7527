Grouping WebElements by id attribute, which has a defined format with partially changing part, and sort the id values.  
============

This is a [Katalon Studio](https://www.katalon.com/) project for demonstration purpose.
You can download the zip file from the [releases] page to your PC, and run it with your Katalon Studio.

This project was developed with Katalon Studio version 5.7.1.

This project was developed in order to propse a solution to a question raised by a comment to the following discussion 
in the Katalon Forum.

- https://forum.katalon.com/discussion/7527/how-can-i-handle-dynamic-web-element-id-and-store-it-in-a-variable

Especially, Hamza Abbas raised a question at 11/14/2018

- https://forum.katalon.com/discussion/comment/25677#Comment_25677

Hamza's question was as follows:

>This is the html of object i have captured
>"<span class="select2-selection__rendered" id="select2-bot_task_action_158-container" role="textbox" aria-readonly="true" title="Email">Email</span>"
>But when this object is created again by katalon the id changes to
>"<span class="select2-selection__rendered" id="select2-bot_task_action_159-container" role="textbox" aria-readonly="true" title="Email">Email</span>"
>The xpath stored in object repository is
>//*[ @ id = "select2-bot_task_action_158-container"]
>But when katalon actually tries to click the object 
>xpath has become
>// * [ @ id = "select2-bot_task_action_159-container"]
>what should i do?

# Problem to solve

The target HTML contains multiple `<span>` elements which are almost identical each other except the id attribute value.

The `<span>` elements are inserted or deleted by interactive operation to the page.

The `<span>`'s id attribute value has a fixed format:

1. it starts with a fixed string 'select2-bot_task_action_'
2. which is followed by a digit
3. which is followed by a fixed string '-container'

Hamza wanted to identify a span element which was last inserted into the target HTML.

# What we assume

We will assume that the digit in the id attribute value is allocated when the `<span>` element is inserted in the HTML,
and the digit will be incremented: the new digit will be equal to the last value + 1.

The digit will simply become bigger and bigger. For example, 158,159, ..., 999, 1000, ..., 9999, ..., 99999, ...

The digit does not goes back to some initial value. For example, ..., 998, 999, 0, 1, 2, ...

I am aware this assumption is too naive and likely to break.
But here I assume this because I am not informed of anymore about his Appliation Under Test.

# Solution


# Description








