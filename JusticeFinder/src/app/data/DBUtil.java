package app.data;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.ext.DatabaseFileLockedException;
import com.db4o.ext.DatabaseReadOnlyException;
import com.db4o.ext.Db4oIOException;
import com.db4o.ext.IncompatibleFileFormatException;
import com.db4o.ext.OldFormatException;
import com.db4o.ta.TransparentPersistenceSupport;
import java.io.File;
import java.nio.file.Paths;


public class DBUtil {

    private static final String FILENAME = Paths.get("Database.db4o").toAbsolutePath().toString();
    private static DBUtil dbUtil;
    
    public synchronized static DBUtil getInstance(){
        if (dbUtil == null){
            dbUtil = new DBUtil();
        }
        return dbUtil;
    }

    private ObjectContainer createConnection() {
        try {

            EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
            config.common().add(new TransparentPersistenceSupport());
            config.common().activationDepth(Integer.MAX_VALUE);
            config.common().updateDepth(Integer.MAX_VALUE);

            config.common().objectClass(Network.class).cascadeOnUpdate(true);

            ObjectContainer db = Db4oEmbedded.openFile(config, FILENAME);
            return db;
        } catch (Db4oIOException | DatabaseFileLockedException | IncompatibleFileFormatException | OldFormatException | DatabaseReadOnlyException ex) {
            System.out.print(ex.getMessage());
        }
        return null;
    }

    public synchronized void storeSystem(Network store) {
        
        File file = new File(FILENAME); 
        file.delete();
        
        ObjectContainer conn = createConnection();
        conn.commit();
        conn.store(store);
        conn.commit();
        conn.close();
    }
    
    public Network retrieveSystem(){
        ObjectContainer conn = createConnection();
        ObjectSet<Network> systems = conn.query(Network.class);
        Network system = null;
        if (systems.size() > 0){
            system = systems.get(systems.size() - 1);
        }
        conn.close();
        return system;
    }
}
