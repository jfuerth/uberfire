package org.uberfire.security.impl.authz;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.uberfire.config.UberFire;
import org.uberfire.security.authz.AuthorizationManager;

@ApplicationScoped
public class AuthzManagerSelector {

    @Inject @UberFire
    private Instance<AuthorizationManager> appSuppliedAuthzManager;

    private AuthorizationManager selectedAuthzManager;

    @Produces @ApplicationScoped
    public AuthorizationManager getAuthzManager() {
        AuthorizationManager found;
        if ( selectedAuthzManager != null ) {
            found = selectedAuthzManager;
        } else if ( !appSuppliedAuthzManager.isUnsatisfied() ) {
            found = appSuppliedAuthzManager.get();
        } else {
            found = new DefaultAuthorizationManager( decisionManagers, resourceManager, votingStrategy, roleDecisionManager );
        }
        return found;
    }
}
