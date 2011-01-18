/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2010, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.jboss.as.security.service;

import javax.security.auth.login.Configuration;

import org.jboss.as.security.SecuritySubsystemElement;
import org.jboss.logging.Logger;
import org.jboss.msc.service.Service;
import org.jboss.msc.service.ServiceName;
import org.jboss.msc.service.StartContext;
import org.jboss.msc.service.StartException;
import org.jboss.msc.service.StopContext;

/**
 * Jaas service to install a {@code Configuration}
 *
 * @author <a href="mailto:mmoyses@redhat.com">Marcus Moyses</a>
 */
public class JaasConfigurationService implements Service<Configuration> {

    public static final ServiceName SERVICE_NAME = SecuritySubsystemElement.JBOSS_SECURITY.append("jaas");

    private static final Logger log = Logger.getLogger("org.jboss.as.security");

    private Configuration configuration;

    public JaasConfigurationService(Configuration configuration) {
        this.configuration = configuration;
    }

    /** {@inheritDoc} */
    public void start(StartContext arg0) throws StartException {
        if (log.isDebugEnabled())
            log.debug("Starting JaasConfigurationService");

        // set new configuration
        Configuration.setConfiguration(configuration);
    }

    /** {@inheritDoc} */
    public void stop(StopContext arg0) {
        // restore configuration to null
        Configuration.setConfiguration(null);
    }

    /** {@inheritDoc} */
    public Configuration getValue() throws IllegalStateException, IllegalArgumentException {
        return configuration;
    }

}
