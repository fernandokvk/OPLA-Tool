package br.ufpr.dinf.gres.architecture.io;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Paths;

/**
 * Classe responsável por acesso ao arquivo de configuração
 * <b>application.yaml</b>/
 *
 * @author edipofederle<edipofederle@gmail.com>
 */
@Deprecated
public class ReaderConfig {

    private static final Logger LOGGER = Logger.getLogger(ReaderConfig.class);
    public static String newPathToConfigurationFile;
    private static DirTarget dir;
    private static String dirTarget;
    private static String dirExportTarget;
    private static String pathToProfileSMarty;
    private static String pathToProfileConcerns;
    private static String pathToTemplateModelsDirectory;
    private static String pathToProfileRelationships;
    private static String pathToProfilePatterns;

    public static void setPathToConfigurationFile(String newPath) {
        newPathToConfigurationFile = newPath;
    }

    /**
     * Diretorio onde a br.ufpr.dinf.gres.arquitetura sera salva para manipulacao Este diretorio
     * pode ser qualquer um com acesso de escrita e leitura.
     *
     * @return
     */
    public static String getDirTarget() {
        if (dirTarget != null) {
            return dirTarget;
        }
        return OPLAConfigThreadScope.config.get().getDirectoryToSaveModels();
    }

    public static void setDirTarget(String path) {
        dirTarget = path;
    }

    /**
     * Diretório onde a br.ufpr.dinf.gres.arquitetura será exportada para que possa ser utilizada.
     * Resultado final
     *
     * @return
     */
    public static String getDirExportTarget() {
        if (dirExportTarget != null) {
            return dirExportTarget;
        }
        return OPLAConfigThreadScope.config.get().getDirectoryToExportModels();
    }

    public static void setDirExportTarget(String path) {
        dirExportTarget = path;
    }

    /**
     * Path pra o arquivo de profile do SMarty
     *
     * @return
     */
    public static String getPathToProfileSMarty() {
        if (pathToProfileSMarty != null) {
            return pathToProfileSMarty;
        }
        return OPLAConfigThreadScope.config.get().getPathToProfile();
    }

    public static void setPathToProfileSMarty(String path) {
        pathToProfileSMarty = path;
    }

    /**
     * Path para o arquivo de profile contendo os concerns.
     *
     * @return
     */
    public static String getPathToProfileConcerns() {
        if (pathToProfileConcerns != null) {
            return pathToProfileConcerns;
        }
        return OPLAConfigThreadScope.config.get().getPathToProfileConcern();
    }

    public static void setPathToProfileConcerns(String path) {
        pathToProfileConcerns = path;
    }

    /**
     * Path para um diretorio contendo os tres arquivos que são usados como
     * template para geração da br.ufpr.dinf.gres.arquitetura. Estes arquivos contem somente um
     * esqueleto. Estes arquivos se encontram na raiz do projeto na pasta
     * filesTemplates. Você pode copiar os arquivos e colocar em qualquer
     * diretório com permissão de leitura.
     *
     * @return
     */
    public static String getPathToTemplateModelsDirectory() {
        if (pathToTemplateModelsDirectory != null) {
            return pathToTemplateModelsDirectory;
        }
        return OPLAConfigThreadScope.config.get().getPathToTemplateModelsDirectory();
    }

    public static void setPathToTemplateModelsDirectory(String path) {
        pathToTemplateModelsDirectory = path;
    }

    /**
     * Verifica se existe o perfil smarty configurado
     *
     * @return boolean
     */
    public static boolean hasSmartyProfile() {
        return !OPLAConfigThreadScope.config.get().getPathToProfile().isEmpty() && getPathToProfileSMarty() != null;
    }

    /**
     * Verifica se existe o perfil concerns configurado
     *
     * @return boolean
     */
    public static boolean hasConcernsProfile() {
        return !OPLAConfigThreadScope.config.get().getPathToProfileConcern().isEmpty() && getPathToProfileConcerns() != null;
    }

    /**
     * Verifica se existe o perfil concerns configurado
     *
     * @return boolean
     */
    public static boolean hasRelationsShipProfile() {
        return !OPLAConfigThreadScope.config.get().getPathToProfileRelationships().isEmpty() && getPathToProfileRelationships() != null;
    }

    public static boolean hasPatternsProfile() {
        return !OPLAConfigThreadScope.config.get().getPathToProfilePatterns().isEmpty() && getPathToProfilePatterns() != null;
    }

    public static String getPathToProfileRelationships() {
        if (pathToProfileRelationships != null)
            return pathToProfileRelationships;
        return OPLAConfigThreadScope.config.get().getPathToProfileRelationships();
    }

    public static void setPathProfileRelationship(String path) {
        pathToProfileRelationships = path;
    }

    public static String getPathToProfilePatterns() {
        if (pathToProfilePatterns != null)
            return pathToProfilePatterns;
        return OPLAConfigThreadScope.config.get().getPathToProfilePatterns();
    }

    public static void setPathToProfilePatterns(String path) {
        pathToProfilePatterns = path;
    }

    public static String getNewPathToConfigurationFile() {
        return newPathToConfigurationFile;
    }

    public static void setNewPathToConfigurationFile(String newPath) {
        newPathToConfigurationFile = newPath;
    }

    public static void load() {
        try {
            Yaml yaml = new Yaml();
            if (StringUtils.isNotBlank(newPathToConfigurationFile)) {
                dir = yaml.loadAs(new FileInputStream(Paths.get(newPathToConfigurationFile).toFile()), DirTarget.class);
                LOGGER.info("New Path" + dir);
            } else {
                InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("config/application.yaml");
                dir = yaml.loadAs(inputStream, DirTarget.class);
                LOGGER.info("Default Path " + dir);
            }
        } catch (Exception e) {
            LOGGER.error("I can't read the configuration file: application.yaml");
        }
    }

}