package org.uberfire.config;

import static java.lang.annotation.ElementType.*;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

import org.uberfire.security.authz.AuthorizationManager;

/**
 * CDI qualifier that indicates an application-supplied type should override the default framework-provided one. Works
 * equally well on bean types, producer methods, and producer fields.
 *
 * <h3>Example</h3>
 *
 * An application provides its own implementation of {@link AuthorizationManager}, and wants the framework to use that
 * one instead of the default:
 *
 * <pre>
 * {@code @ApplicationScoped}
 * {@code @UberFire}
 * public class MyAuthorizationManager implements AuthorizationManager {
 *
 *   ...
 *
 * }
 * </pre>
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({FIELD, TYPE, METHOD})
public @interface UberFire {

}
