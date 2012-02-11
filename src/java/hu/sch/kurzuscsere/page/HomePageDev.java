package hu.sch.kurzuscsere.page;

import hu.sch.kurzuscsere.panel.LastRequestsPanel;

/**
 *
 * @author balo
 */
public class HomePageDev extends BasePage {

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new LastRequestsPanel("lastRequestsPanel"));
    }

    @Override
    protected String getPageTitle() {
        return "Kezdőlap (Új)";
    }
}
