# Wikipedia Philosophy


A Java command line app which, given a valid Wikipedia.org article URL, will tell you how many first-link clicks that article is from [wikipedia.org/wiki/Philosophy](wikipedia.org/wiki/Philosophy).

~95% percent of English-language Wikipedia articles eventually link through to the Philosophy article. 

![demo](https://ramonaharrison.github.io/images/wiki.gif)

## Usage

Clone, compile and run.
At the prompt, input a valid Wikipedia URL (including the protocol http://), for example: http://en.wikipedia.org/wiki/Cat .

Uses the jsoup library - [http://jsoup.org/](http://jsoup.org/).

## How does this work? 

As the Wikipedia article on [“Wikipedia: Getting to Philosophy”](http://en.wikipedia.org/wiki/Wikipedia:Getting_to_Philosophy) explains, the Wikipedia Manual of Style recommends that each article should start by defining the topic of the article. This creates a likelihood that the first link will lead to a broader subject, eventually leading to the broadest of all subjects, philosophy. It’s cited that as of May 2011, 94.52% of articles lead to Philosophy. The median number of clicks is just 23.

## The rules:

To try it out in your browser, pick any article and follow the first link from each subsequent article. A first link is defined as the first link in the body of the article that is:

* Non-parenthesized
* Non-italicized
* Not external
* Not the current page
* Not a "red link" (non-existent entry)

## More Info:

[http://ramonaharrison.github.io/accesscode/java/http/wikipedia/2015/03/27/wikipedia-philosophy/](http://ramonaharrison.github.io/accesscode/java/http/wikipedia/2015/03/27/wikipedia-philosophy/)

