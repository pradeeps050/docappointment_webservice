package org.ps.docappointment.authentication;

import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;

import javax.ws.rs.NameBinding;
 


@NameBinding
@Retention(RUNTIME)
//@Target({ElmentType.METHOD, ElementType.TYPE})
public @interface Secured {

}
