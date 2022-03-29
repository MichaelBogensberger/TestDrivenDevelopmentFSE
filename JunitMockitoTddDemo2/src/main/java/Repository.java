import java.util.Set;

public interface Repository<ENTITY,ID> {
    void speichere(ENTITY produkt);
    ENTITY ladeMitId(ID id);
    Set<ENTITY> ladeAlle();
}
