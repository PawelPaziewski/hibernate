package DAOs;

import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;
import java.util.function.Function;

public abstract class GenericDao<R, T extends Serializable> {

    public R findById(T id) {
        return doInTx(s -> s.get(getClazz(), id));
    }

    public List<R> findAll() {
        return doInTx(s -> s.createQuery("select e from " + getClazz().getName() + " e", getClazz()).getResultList());
    }

    protected abstract Class<R> getClazz();

    protected <L> L doInTx(Function<Session, L> fun) {
        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        L result = fun.apply(session);
        session.getTransaction().commit();
        session.close();
        return result;
    }
}
