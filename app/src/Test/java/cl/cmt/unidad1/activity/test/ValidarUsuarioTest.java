package cl.cmt.unidad1.activity.test;
import junit.framework.TestCase;

import cl.cmt.unidad1.clases.Usuario;

/**
 * Created by Cristian on 12-07-2015.
 */
public class ValidarUsuarioTest extends TestCase {
    @Override
    public void setUp() throws Exception {
        super.setUp();
    }
    public void testValidarUsuario() throws Exception {
        Usuario testClass= new Usuario();
        assertEquals("juan","juan");

    }
}
