import com.kryvoruchka.View;

public class Main {
    public static void main(final String[] args) {

        System.out.println(org.hibernate.Version.getVersionString());
        View view = new View();
        view.show();
    }
}