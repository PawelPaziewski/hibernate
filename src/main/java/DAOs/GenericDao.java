package DAOs;

import org.hibernate.Session;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    public List<R> findByIds(T[] ids) {
        final List<T> idsList = Arrays.stream(ids).collect(Collectors.toList());
        return doInTx(s -> s.createQuery("select e from " + getClazz().getName() + " e where e.id in :ids", getClazz())
                .setParameter("ids", idsList)
                .getResultList());
    }

    public void save(R entity) {
        doInTx(s -> s.save(entity));
    }
}
