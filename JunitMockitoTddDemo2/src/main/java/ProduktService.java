import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class ProduktService {

    Repository<Produkt,UUID> repository;

    public ProduktService(Repository repository)
    {
        this.repository = repository;
    }

    public void produktSpeichern(Produkt produkt)
    {
        this.repository.speichere(produkt);
    }

    public void produktHolenMitId(UUID id) {
        this.repository.ladeMitId(id);
    }

    public Set<Produkt> ladeAlleProdukte()
    {
        return this.repository.ladeAlle();
    }

    public Set<Produkt> holeAlleProdukteMitKommentar()
    {
        Set<Produkt> produkte = this.ladeAlleProdukte();
        return produkte.stream().filter(produkt -> produkt.anzahlKommentare()>0).collect(Collectors.toSet());
    }
}
