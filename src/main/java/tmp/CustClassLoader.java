package tmp;

/**
 * Title: CustClassLoader
 * Description:
 * author: liujie
 * date: 2017-08-03 上午10:07
 */
public class CustClassLoader extends ClassLoader{

    /**
     * Loads the class with the specified <a href="#name">binary name</a>.
     * This method searches for classes in the same manner as the {@link
     * #loadClass(String, boolean)} method.  It is invoked by the Java virtual
     * machine to resolve class references.  Invoking this method is equivalent
     * to invoking {@link #loadClass(String, boolean) <tt>loadClass(name,
     * false)</tt>}.
     *
     * @param name The <a href="#name">binary name</a> of the class
     * @return The resulting <tt>Class</tt> object
     * @throws ClassNotFoundException If the class was not found
     */
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {f'f'f'd'd'BCCCCCCCCCCCCC
        return super.loadClass(name);
    }
}
