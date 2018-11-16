package entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * @author: gsj
 * @version: 1.0
 * @date: created on 2018/11/1
 */
public class myList<E> extends ArrayList<E> {

    public myList() {
    }

    public myList(Collection<? extends E> c) {
        super(c);
    }

    @Override
    public String toString() {
        Iterator<E> it = iterator();
        if (!it.hasNext())
            return "[]";

        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (; ; ) {
            E e = it.next();
//            sb.append(e == this ? "(this Collection)" : e);
            if (e == this) {
                sb.append("(this Collection)");
            } else {
                sb.append(String.format("%5s", String.valueOf(e)));
            }
            if (!it.hasNext())
                return sb.append(']').toString();
            sb.append(',');
        }
    }
}
