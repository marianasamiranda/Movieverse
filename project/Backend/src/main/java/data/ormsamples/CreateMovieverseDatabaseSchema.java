/**
 * Licensee: mariana(Universidade do Minho)
 * License Type: Academic
 */
package data.ormsamples;

import org.orm.*;
public class CreateMovieverseDatabaseSchema {
    public static void main(String[] args) {
        try {
            ORMDatabaseInitiator.createSchema(data.movieverse.MovieversePersistentManager.instance());
            data.movieverse.MovieversePersistentManager.instance().disposePersistentManager();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
