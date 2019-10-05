
import groovy.lang.Closure;

void justDoIt(String name, Closure cl) {
    cl(name);
}
