import org.hibernate.SessionFactory;

public class Main {

    public static void main(String[] args) {
        final SessionFactory factory = HibernateUtil.getSessionFactory();
        System.out.println("TEST");
    }
}
