package hu.sch.kurzuscsere.page;

import hu.sch.kurzuscsere.panel.FooterPanel;
import hu.sch.kurzuscsere.panel.HeaderPanel;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

/**
 *
 * @author Kresshy
 * @version
 */
public abstract class BasePage extends WebPage {

    public BasePage() {
        super();
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new FeedbackPanel("feedbackPanel"));
        add(new HeaderPanel("headerPanel", "KurzusCsere"));
        add(new FooterPanel("footerPanel", "Powered by Kir-Dev and created by Kresshy"));

    }
}
