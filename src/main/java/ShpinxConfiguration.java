import edu.cmu.sphinx.api.Configuration;

enum LangType {
    English,
    Russian
}

public class ShpinxConfiguration {

    private Configuration configuration;

    public ShpinxConfiguration(LangType type) {
        configuration = new Configuration();
        // Set path to the acoustic model.

        switch (type) {
            case English:
                configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
                configuration.setDictionaryPath("resource:/7321.dic");
                configuration.setLanguageModelPath("resource:/7321.lm");
                break;
            case Russian:
                configuration.setAcousticModelPath("resource:/ru-model");
                configuration.setDictionaryPath("resource:/ru-model/ru.dic");
                configuration.setLanguageModelPath("resource:/ru-model/ru.lm");
                break;
            default:
        }
    }

    public Configuration getConfiguration() {
        return configuration;
    }
}
