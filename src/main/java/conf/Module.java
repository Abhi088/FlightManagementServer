package conf;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

import ninja.jpa.JpaModule;
import ninja.migrations.MigrationClassicModule;
import ninja.migrations.MigrationEngine;
import ninja.migrations.MigrationEngineProvider;
import ninja.migrations.MigrationInitializer;
import ninja.utils.NinjaProperties;



@Singleton
public class Module extends AbstractModule {
    
	private final NinjaProperties ninjaProperties;
    
    public Module(NinjaProperties ninjaProperties) {
        	this.ninjaProperties = ninjaProperties;
    }
	
	 @Override
	 protected void configure() {
	        
	        install(new JpaModule(ninjaProperties));
	        install(new MigrationClassicModule());
	        bind(MigrationEngine.class).toProvider(MigrationEngineProvider.class);
	        bind(MigrationInitializer.class).asEagerSingleton();
	  }

}
