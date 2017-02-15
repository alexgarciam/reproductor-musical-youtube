package es.alex.futvre.persistence;

import java.io.Serializable;
import java.util.List;

import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.transaction.annotation.Transactional;

public abstract class GenericDao<T, ID extends Serializable>  {

  private JpaTemplate jpaTemplate;

  private Class<T> persistentClass;

  public GenericDao(Class<T> persistentClass) {
    this.persistentClass = persistentClass;
  }

  public void setJpaTemplate(JpaTemplate jpaTemplate) {
    this.jpaTemplate = jpaTemplate;
  }

  protected JpaTemplate getJpaTemplate() {
    return jpaTemplate;
  }

  @Transactional
  public void merge(T entity) {
    jpaTemplate.merge(entity);
  }

  @Transactional
  public void persist(T entity) {
    jpaTemplate.persist(entity);
  }

  @Transactional
  public void remove(T entity, int id) {
	  getJpaTemplate().remove(getJpaTemplate().getReference(entity.getClass(), id));
	 // jpaTemplate.remove(entity);
  }

  @Transactional(readOnly = true)
  public T findById(ID id) {
    return jpaTemplate.find(getPersistentClass(), id);
  }

  @SuppressWarnings("unchecked")
  @Transactional(readOnly = true)
  public List<T> findAll() {
    return jpaTemplate.find("from " + getPersistentClass().getSimpleName());
  }

  public Class<T> getPersistentClass() {
        return persistentClass;
    }
  
  @SuppressWarnings("unchecked")
  @Transactional(readOnly = true)
  public List<T> find(String query) {
          return getJpaTemplate().find(query);

  }

}
