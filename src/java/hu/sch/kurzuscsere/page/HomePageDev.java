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

        //add(new LastPairings("lastPairingsPanel"));
        add(new LastRequestsPanel("lastRequestsPanel"));
    }
}
