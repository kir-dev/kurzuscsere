/**
 * Copyright (c) 2008-2010, Peter Major All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met: *
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer. * Redistributions in binary
 * form must reproduce the above copyright notice, this list of conditions and
 * the following disclaimer in the documentation and/or other materials provided
 * with the distribution. * Neither the name of the Peter Major nor the names of
 * its contributors may be used to endorse or promote products derived from this
 * software without specific prior written permission. * All advertising
 * materials mentioning features or use of this software must display the
 * following acknowledgement: This product includes software developed by the
 * Kir-Dev Team, Hungary and its contributors.
 *
 * THIS SOFTWARE IS PROVIDED BY Peter Major ''AS IS'' AND ANY EXPRESS OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO
 * EVENT SHALL Peter Major BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR
 * OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package hu.sch.kurzuscsere.authz;

import hu.sch.kurzuscsere.domain.User;
import org.apache.wicket.Application;
import org.apache.wicket.Request;
import org.apache.wicket.protocol.http.WebApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Fejlesztői teszt autorizációs modul.
 *
 * @author hege
 * @author balo
 */
public final class DummyAuthorization implements UserAuthorization {

    public static User actualTestUser = new User();
    /**
     * A logoláshoz szükséges logger.
     */
    private static final Logger log = LoggerFactory.getLogger(DummyAuthorization.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(Application wicketApplication) {
        if (wicketApplication.getConfigurationType().equals(WebApplication.DEPLOYMENT)) {
            throw new IllegalStateException("Do not use dummy authz module in production environment!");
        }

        log.warn("Dummy authorization mode initiated successfully");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getRemoteUser(Request wicketRequest) {
        return actualTestUser.getNick();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User getUserAttributes(Request wicketRequest) {
        User user = new User();

        user.setEmail(actualTestUser.getEmail());
        user.setNick(getRemoteUser(wicketRequest));

        user.setName(actualTestUser.getName());

        return user;
    }
}
