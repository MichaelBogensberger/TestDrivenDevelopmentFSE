import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class InMemoryRepository implements Repository<Produkt,UUID> {

    private HashMap<UUID,Produkt> inMemoryDb;

    public InMemoryRepository() {
        this.inMemoryDb = new HashMap<>();
    }

    private boolean produktVorhanden(Produkt produkt)
    {
        //TODO
        return true;
    }

    @Override
    public void speichere(Produkt produkt) {
        //TODO
    }

    @Override
    public Produkt ladeMitId(UUID uuid) {
        //TODO
        return null;
    }

    @Override
    public Set<Produkt> ladeAlle() {
        //TODO
        return null;
    }
}
