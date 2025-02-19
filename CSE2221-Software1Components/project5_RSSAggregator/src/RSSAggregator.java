import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program reads numerous RSS feeds and creates an HTML index page containing
 * links to the individual feed pages, as well as a well written HTML page of
 * links for each feed.
 *
 * @author Saeed Alneyadi
 *
 */
public final class RSSAggregator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private RSSAggregator() {
    }

    /**
     * Outputs the "opening" tags in the generated HTML file. These are the
     * expected elements generated by this method:
     *
     * <html> <head> <title>the channel tag title as the page title</title>
     * </head> <body>
     * <h1>the page title inside a link to the <channel> link</h1>
     * <p>
     * the channel description
     * </p>
     * <table border="1">
     * <tr>
     * <th>Date</th>
     * <th>Source</th>
     * <th>News</th>
     * </tr>
     *
     * @param channel
     *            the channel element XMLTree
     * @param out
     *            the output stream
     * @updates out.content
     * @requires [the root of channel is a <channel> tag] and out.is_open
     * @ensures out.content = #out.content * [the HTML "opening" tags]
     */
    private static void outputHeader(XMLTree channel, SimpleWriter out) {
        assert channel != null : "Violation of: channel is not null";
        assert out != null : "Violation of: out is not null";
        assert channel.isTag() && channel.label().equals("channel") : ""
                + "Violation of: the label root of channel is a <channel> tag";
        assert out.isOpen() : "Violation of: out.is_open";

        /*
         * Creating XMLTree object for the <title>, <link>, and <description>
         * tags.
         */
        XMLTree title = channel.child(getChildElement(channel, "title"));
        XMLTree link = channel.child(getChildElement(channel, "link"));
        XMLTree description = channel
                .child(getChildElement(channel, "description"));

        /*
         * Variables Initialization
         */
        String chanTitle = "";
        String chanDesc = "";

        /*
         * IF <title> has a child, the label of the child will be assigned to
         * (chanTitle). IF it is not, "Empty Title" will be assigned.
         */
        if (title.numberOfChildren() > 0) {
            chanTitle = title.child(0).label();
        } else {
            chanDesc = "Empty Title";
        }

        /*
         * Writing the page title code using (chanTitle).
         */
        out.println("<html> <head> <title>" + chanTitle + "</title>");
        out.println("</head> <body>");

        /*
         * Writing the page header code using (chanTitle) and label of the
         * <link> child.
         */
        out.println("<h1><a href=\"" + link.child(0).label() + "\">" + chanTitle
                + "</a></h1>");

        /*
         * IF <description> has a child, the label of the child will be assigned
         * to (chanDesc). IF it is not, "No description" will be assigned.
         */
        if (description.numberOfChildren() > 0) {
            chanDesc = description.child(0).label();
        } else {
            chanDesc = "No description";
        }

        /*
         * Writing the HTML code of <description> child label and the table
         * header cells inside the output file.
         */
        out.println("<p>");
        out.println(chanDesc);
        out.println("</p>");
        out.println("<table border=\"1\">");
        out.println("<tr>");
        out.println("<th>Date</th>");
        out.println("<th>Source</th>");
        out.println("<th>News</th>");
        out.println("</tr>");
    }

    /**
     * Outputs the "closing" tags in the generated HTML file. These are the
     * expected elements generated by this method:
     *
     * </table>
     * </body> </html>
     *
     * @param out
     *            the output stream
     * @updates out.contents
     * @requires out.is_open
     * @ensures out.content = #out.content * [the HTML "closing" tags]
     */
    private static void outputFooter(SimpleWriter out) {
        assert out != null : "Violation of: out is not null";
        assert out.isOpen() : "Violation of: out.is_open";

        /*
         * Writing the last closing tags of the HTML code inside the output
         * file.
         */
        out.println("</table>");
        out.println("</body> </html>");
    }

    /**
     * Finds the first occurrence of the given tag among the children of the
     * given {@code XMLTree} and return its index; returns -1 if not found.
     *
     * @param xml
     *            the {@code XMLTree} to search
     * @param tag
     *            the tag to look for
     * @return the index of the first child of type tag of the {@code XMLTree}
     *         or -1 if not found
     * @requires [the label of the root of xml is a tag]
     * @ensures <pre>
     * getChildElement =
     *  [the index of the first child of type tag of the {@code XMLTree} or
     *   -1 if not found]
     * </pre>
     */
    private static int getChildElement(XMLTree xml, String tag) {
        assert xml != null : "Violation of: xml is not null";
        assert tag != null : "Violation of: tag is not null";
        assert xml.isTag() : "Violation of: the label root of xml is a tag";

        /*
         * Variable Initialization
         */
        int childNum = -1;

        /*
         * This WHILE loop searches for the first XML <tag> that has this label
         * (tag) inside the XMLTree (xml).
         */
        int childCount = 0;
        while (childCount < xml.numberOfChildren() && (childNum == -1)) {
            if ((xml.child(childCount).label()).equals(tag)) {
                childNum = childCount;
            }

            childCount++;
        }

        /*
         * RETURN statement: Returning (childNum).
         */
        return childNum;
    }

    /**
     * Processes one news item and outputs one table row. The row contains three
     * elements: the publication date, the source, and the title (or
     * description) of the item.
     *
     * @param item
     *            the news item
     * @param out
     *            the output stream
     * @updates out.content
     * @requires [the label of the root of item is an <item> tag] and
     *           out.is_open
     * @ensures <pre>
     * out.content = #out.content *
     *   [an HTML table row with publication date, source, and title of news item]
     * </pre>
     */
    private static void processItem(XMLTree item, SimpleWriter out) {
        assert item != null : "Violation of: item is not null";
        assert out != null : "Violation of: out is not null";
        assert item.isTag() && item.label().equals("item") : ""
                + "Violation of: the label root of item is an <item> tag";
        assert out.isOpen() : "Violation of: out.is_open";

        /*
         * Getting Child Elements indexes by calling (getChildElement) function
         * for <title> <link>, <description>, <pubDate>, and <source> tags,
         * where each index has a variable.
         */
        int titleIdx = getChildElement(item, "title");
        int linkIdx = getChildElement(item, "link");
        int descIdx = getChildElement(item, "description");
        int pubIdx = getChildElement(item, "pubDate");
        int sourceIdx = getChildElement(item, "source");

        /*
         * Writing the opening HTML tag of table row.
         */
        out.println("<tr>");

        /*
         * Writes the first cell of the table row code with <pubDate> tag info.
         */
        if (pubIdx != -1) {
            out.println("<td>" + item.child(pubIdx).child(0).label() + "</td>");
        } else {
            out.println("<td>No date available</td>");
        }

        /*
         * Writes the second cell of the table row code with <source> tag info.
         */
        if (sourceIdx != -1) {
            String sourceName = "No Source Name";

            if (item.child(sourceIdx).numberOfChildren() > 0
                    && !(item.child(sourceIdx).child(0).label().equals(""))) {
                sourceName = item.child(sourceIdx).child(0).label();
            }

            out.println("<td><a href=\""
                    + item.child(sourceIdx).attributeValue("url") + "\">"
                    + sourceName + "</a></td>");
        } else {
            out.println("<td>No source available</td>");
        }

        /*
         * Writes the third cell of the table row code with <source> tag info.
         */
        if (titleIdx != -1 && item.child(titleIdx).numberOfChildren() > 0
                && !(item.child(titleIdx).child(0).label().equals(""))) {
            out.print("<td>");

            String title = item.child(titleIdx).child(0).label();

            if (linkIdx != -1) {
                out.print("<a href=\"" + item.child(linkIdx).child(0).label()
                        + "\">");
                out.print(title);
                out.print("</a>");
            } else {
                out.print(title);
            }

            out.println("</td>");

        } else if (descIdx != -1 && item.child(descIdx).numberOfChildren() > 0
                && !(item.child(descIdx).child(0).label().equals(""))) {
            out.print("<td>");

            String description = item.child(descIdx).child(0).label();

            if (linkIdx != -1) {
                out.print("<a href=\"" + item.child(linkIdx).child(0).label()
                        + "\">");
                out.print(description);
                out.print("</a>");
            } else {
                out.print(description);
            }

            out.println("</td>");
        } else {
            out.print("<td>");

            if (linkIdx != -1) {
                out.print("<a href=\"" + item.child(linkIdx).child(0).label()
                        + "\">");
                out.print("No title available");
                out.print("</a>");
            } else {
                out.print("No title available");
            }

            out.println("</td>");
        }

        /*
         * Writing the closing HTML tag of table row.
         */
        out.println("</tr>");
    }

    /**
     * Processes one XML RSS (version 2.0) feed from a given URL converting it
     * into the corresponding HTML output file.
     *
     * @param url
     *            the URL of the RSS feed
     * @param file
     *            the name of the HTML output file
     * @param out
     *            the output stream to report progress or errors
     * @updates out.content
     * @requires out.is_open
     * @ensures <pre>
     * [reads RSS feed from url, saves HTML document with table of news items
     *   to file, appends to out.content any needed messages]
     * </pre>
     */
    private static void processFeed(String url, String file, SimpleWriter out) {
        /*
         * Open Output stream inside "fileName" file.
         */
        SimpleWriter feedOut = new SimpleWriter1L(file);

        /*
         * Read XML input and initialize XMLTree. If input is not legal XML,
         * this statement will fail.
         */
        XMLTree xml = new XMLTree1(url);

        if (xml.label().equals("rss") && xml.hasAttribute("version")
                && xml.attributeValue("version").equals("2.0")) {
            /*
             * Extract <channel> element from (xml) tree.
             */
            XMLTree channel = xml.child(0);

            /*
             * Writing the HTML header code inside the output file by calling
             * (outputHeader) procedure.
             */
            outputHeader(channel, feedOut);

            /*
             * Writing the HTML table code inside the output file by calling
             * (processItem) procedure inside a FOR loop.
             */
            for (int i = 0; i < channel.numberOfChildren(); i++) {
                if (channel.child(i).label().equals("item")) {
                    processItem(channel.child(i), feedOut);
                }
            }

            /*
             * Writing the HTML footer code (closing tags) inside the output
             * file by calling (outputFooter) procedure.
             */
            outputFooter(feedOut);

            /*
             * Outputting Process Success Message.
             */
            out.println("");
            out.println("The \"" + file
                    + "\" file is generated from the RSS Feed SUCCESSFULLY.");

        } else {
            /*
             * Outputting Process Failure Message.
             */
            out.println("");
            out.println(
                    "The Process of this File generation have ran UNSUCCESSFULLY.");
            out.println("Possible Failure Reasons: ");
            out.println("- Given Feed is not RSS.");
            out.println("- The RSS Feed has no \"version\" attribute.");
            out.println("- The RSS Feed \"version\" is not \"2.0\".");

        }

        /*
         * Close output stream.
         */
        feedOut.close();
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        /*
         * Open I/O stream.
         */
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        /*
         * Prompting for the name or the URL of the XML file that contains a
         * list of URLs for RSS v2.0 feeds.
         */
        out.print("Enter the name (with .xml extension) or the URL of the ");
        out.print("XML file that contains a list of URLs for RSS v2.0 feeds: ");
        String url = in.nextLine();

        /*
         * Prompting for the name of an output file (with .html extension) of
         * the XML file.
         */
        out.print("Enter the name of an output file (with .html extension): ");
        String xmlFileName = in.nextLine();

        /*
         * Open Output stream inside "fileName" file.
         */
        SimpleWriter xmlOut = new SimpleWriter1L(xmlFileName);

        /*
         * Read XML input and initialize XMLTree. If input is not legal XML,
         * this statement will fail.
         */
        XMLTree xml = new XMLTree1(url);

        /*
         * Variable Initialization
         */
        String title = "No title";

        if (xml.hasAttribute("title")) {
            title = xml.attributeValue("title");
        }

        /*
         * Writing the code of page title code using (title).
         */
        xmlOut.println("<html> <head> <title>" + title + "</title>");
        xmlOut.println("</head> <body>");

        /*
         * Writing the code of page header code using (title).
         */
        xmlOut.println("<h2>" + title + "</h2>");

        /*
         * Writing the code of the unordered list opening tag.
         */
        xmlOut.println("<ul>");

        for (int feedCounter = 0; feedCounter < xml
                .numberOfChildren(); feedCounter++) {
            /*
             * Extract <feed> element from (xml) tree.
             */
            XMLTree feed = xml.child(feedCounter);

            /*
             * Variables Initializations
             */
            String feedURL = "";
            String feedName = "Unnamed";
            String feedFile = "Unnamed.html";

            if (feed.hasAttribute("url")) {
                feedURL = feed.attributeValue("url");
            }

            if (feed.hasAttribute("name")) {
                feedName = feed.attributeValue("name");
            }

            if (feed.hasAttribute("file")) {
                feedFile = feed.attributeValue("file");
            }

            /*
             * Writing the code of the unordered list element opening tag.
             */
            xmlOut.print("<li>");

            /*
             * Writing the code of the hyperlink HTML tag using (feedURL) and
             * (feedName).
             */
            xmlOut.print("<a href=\"" + feedURL + "\">" + feedName + "</a>");

            /*
             * Writing the code of the unordered list element closing tag.
             */
            xmlOut.println("</li>");

            /*
             * Creating HTML file for the RSS feed by calling (processFeed)
             * procedure.
             */
            processFeed(feedURL, feedFile, out);
        }

        /*
         * Writing the code of the unordered list closing tag.
         */
        xmlOut.println("</ul>");

        /*
         * Writing the code of the body and HTML closing tag.
         */
        xmlOut.println("</body></html>");

        /*
         * Outputting Process Success Message.
         */
        out.println("");
        out.println("The \"" + xmlFileName
                + "\" file is generated from the RSS Feed SUCCESSFULLY.");

        /*
         * Close I/O streams.
         */
        xmlOut.close();
        in.close();
        out.close();
    }

}
