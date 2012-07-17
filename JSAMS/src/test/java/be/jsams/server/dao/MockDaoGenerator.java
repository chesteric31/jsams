package be.jsams.server.dao;

import java.util.ArrayList;
import java.util.List;

import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.ProductCategoryBean;
import be.jsams.server.dao.management.ProductCategoryDao;
import be.jsams.server.model.Civility;
import be.jsams.server.model.LegalForm;
import be.jsams.server.model.Society;
import be.jsams.server.model.management.Agent;
import be.jsams.server.model.management.ProductCategory;

/**
 * Generator of Mock DAOs.
 * 
 * @author chesteric31
 * @version $Revision$ $Date$ $Author$
 */
public final class MockDaoGenerator {
    
    /**
     * Default private constructor for utility class. 
     */
    private MockDaoGenerator() {
        
    }

    /**
     * Generates mock {@link LegalFormDao}.
     * 
     * @param society the {@link Society} to link
     * @return the built {@link LegalFormDao}
     */
    public static LegalFormDao generateMockLegalFormDao(final Society society) {
        return new LegalFormDao() {

            @Override
            public void update(LegalForm transientObject) {
            }

            @Override
            public void flush() {
            }

            @Override
            public LegalForm findById(Long id) {
                return null;
            }

            @Override
            public List<LegalForm> findAll() {
                List<LegalForm> forms = new ArrayList<LegalForm>();
                LegalForm form = society.getLegalForm();
                if (form != null) {
                    forms.add(society.getLegalForm());
                }
                return forms;
            }

            @Override
            public void delete(Long id) {
            }

            @Override
            public void delete(LegalForm persistentObject) {
            }

            @Override
            public LegalForm add(LegalForm newInstance) {
                return null;
            }
        };
    }

    /**
     * Generates mock {@link CivilityDao}.
     * 
     * @param agent the {@link Agent} to link
     * @return the built {@link CivilityDao}
     */
    public static CivilityDao generateMockCivilityDao(final Agent agent) {
        return new CivilityDao() {
            
            @Override
            public void update(Civility transientObject) {
            }
            
            @Override
            public void flush() {
            }
            
            @Override
            public Civility findById(Long id) {
                return null;
            }
            
            @Override
            public List<Civility> findAll() {
                List<Civility> civilities = new ArrayList<Civility>();
                Civility civility = agent.getCivility();
                if (civility != null) {
                    civilities.add(agent.getCivility());
                }
                return civilities;
            }
            
            @Override
            public void delete(Long id) {
            }
            
            @Override
            public void delete(Civility persistentObject) {
            }
            
            @Override
            public Civility add(Civility newInstance) {
                return null;
            }
        };
    }

    /**
     * Generates mock {@link ProductCategoryDao}.
     * 
     * @param category the {@link ProductCategory} to link
     * @return the built {@link ProductCategoryDao}
     */
    public static ProductCategoryDao generateMockProductCategoryDao(final ProductCategory category) {
        return new ProductCategoryDao() {

            @Override
            public void update(ProductCategory transientObject) {
            }

            @Override
            public void flush() {
            }

            @Override
            public ProductCategory findById(Long id) {
                return null;
            }

            @Override
            public List<ProductCategory> findAll() {
                List<ProductCategory> categories = new ArrayList<ProductCategory>();
                categories.add(category);
                return categories;
            }

            @Override
            public void delete(Long id) {
            }

            @Override
            public void delete(ProductCategory persistentObject) {
            }

            @Override
            public ProductCategory add(ProductCategory newInstance) {
                return null;
            }

            @Override
            public List<ProductCategory> findByCriteria(ProductCategoryBean criteria) {
                return null;
            }

            @Override
            public SocietyBean getCurrentSociety() {
                return null;
            }

            @Override
            public void setCurrentSociety(SocietyBean currentSociety) {
            }
        };
    }

}
