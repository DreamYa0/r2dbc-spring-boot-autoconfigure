# Reactive Mysql数据库操作

如果需要使用mysql首先需要在 common模块的pom.xml文件中加入如下maven坐标

```xml
<dependency>
 <groupId>com.g7.framework</groupId>
 <artifactId>r2dbc-spring-boot-autoconfigure</artifactId>
</dependency>
```

**实体类**

```java
/**
 * @author dreamyao
 * @title
 * @date 2021/11/14 10:26 下午
 * @since 1.0.0
 */
@Table(value = "ntocc_oplog")
public class OpLog extends AbstractEntity<Long> implements Serializable {
 
    private static final long serialVersionUID = 7460187435770393679L;
 
    @Column(value = "orgroot")
    private String orgroot;
    @Column(value = "orgcode")
    private String orgcode;
    @Column(value = "log_id")
    private String logId;
    @Column(value = "one_level")
    private Integer oneLevel;
    @Column(value = "two_level")
    private Integer twoLevel;
    @Column(value = "three_level")
    private Integer threeLevel;
    @Column(value = "operator_id")
    private String operatorId;
    @Column(value = "operator_type")
    private Integer operatorType;
    @Column(value = "operator_user_name")
    private String operatorUserName;
    @Column(value = "operator_real_name")
    private String operatorRealName;
    @Column(value = "operator_org_code")
    private String operatorOrgCode;
    @Column(value = "operator_org_name")
    private String operatorOrgName;
    @Column(value = "description")
    private String description;
    @Column(value = "waybill_id")
    private String waybillId;
    @Column(value = "waybill_no")
    private String waybillNo;
    @Column(value = "handle")
    private Integer handle = 0;
 
    public Integer getHandle() {
        return handle;
    }
 
    public void setHandle(Integer handle) {
        this.handle = handle;
    }
 
    public String getOrgroot() {
        return orgroot;
    }
 
    public void setOrgroot(String orgroot) {
        this.orgroot = orgroot;
    }
 
    public String getOrgcode() {
        return orgcode;
    }
 
    public void setOrgcode(String orgcode) {
        this.orgcode = orgcode;
    }
 
    public String getLogId() {
        return logId;
    }
 
    public void setLogId(String logId) {
        this.logId = logId;
    }
 
    public Integer getOneLevel() {
        return oneLevel;
    }
 
    public void setOneLevel(Integer oneLevel) {
        this.oneLevel = oneLevel;
    }
 
    public Integer getTwoLevel() {
        return twoLevel;
    }
 
    public void setTwoLevel(Integer twoLevel) {
        this.twoLevel = twoLevel;
    }
 
    public Integer getThreeLevel() {
        return threeLevel;
    }
 
    public void setThreeLevel(Integer threeLevel) {
        this.threeLevel = threeLevel;
    }
 
    public String getOperatorId() {
        return operatorId;
    }
 
    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }
 
    public Integer getOperatorType() {
        return operatorType;
    }
 
    public void setOperatorType(Integer operatorType) {
        this.operatorType = operatorType;
    }
 
    public String getOperatorUserName() {
        return operatorUserName;
    }
 
    public void setOperatorUserName(String operatorUserName) {
        this.operatorUserName = operatorUserName;
    }
 
    public String getOperatorRealName() {
        return operatorRealName;
    }
 
    public void setOperatorRealName(String operatorRealName) {
        this.operatorRealName = operatorRealName;
    }
 
    public String getOperatorOrgCode() {
        return operatorOrgCode;
    }
 
    public void setOperatorOrgCode(String operatorOrgCode) {
        this.operatorOrgCode = operatorOrgCode;
    }
 
    public String getOperatorOrgName() {
        return operatorOrgName;
    }
 
    public void setOperatorOrgName(String operatorOrgName) {
        this.operatorOrgName = operatorOrgName;
    }
 
    public String getDescription() {
        return description;
    }
 
    public void setDescription(String description) {
        this.description = description;
    }
 
    public String getWaybillId() {
        return waybillId;
    }
 
    public void setWaybillId(String waybillId) {
        this.waybillId = waybillId;
    }
 
    public String getWaybillNo() {
        return waybillNo;
    }
 
    public void setWaybillNo(String waybillNo) {
        this.waybillNo = waybillNo;
    }
 
    @Nullable
    public Long getId() {
        return id;
    }
 
    @Override
    public boolean isNew() {
        return Objects.isNull(id);
    }
 
    public void setId(@Nullable Long id) {
        this.id = id;
    }
 
    @Nullable
    public LocalDateTime getGmtCreate() {
        return gmtCreate;
    }
 
    public void setGmtCreate(@Nullable LocalDateTime gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
 
    @Nullable
    public LocalDateTime getGmtModified() {
        return gmtModified;
    }
 
    public void setGmtModified(@Nullable LocalDateTime gmtModified) {
        this.gmtModified = gmtModified;
    }
 
    @Override
    public int hashCode() {
        return 730724695;
    }
 
    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + getId() + ", " +
                "orgroot = " + getOrgroot() + ", " +
                "orgcode = " + getOrgcode() + ", " +
                "logId = " + getLogId() + ", " +
                "oneLevel = " + getOneLevel() + ", " +
                "twoLevel = " + getTwoLevel() + ", " +
                "threeLevel = " + getThreeLevel() + ", " +
                "operatorId = " + getOperatorId() + ", " +
                "operatorType = " + getOperatorType() + ", " +
                "operatorUserName = " + getOperatorUserName() + ", " +
                "operatorRealName = " + getOperatorRealName() + ", " +
                "operatorOrgCode = " + getOperatorOrgCode() + ", " +
                "operatorOrgName = " + getOperatorOrgName() + ", " +
                "desc = " + getDescription() + ", " +
                "waybillId = " + getWaybillId() + ", " +
                "waybillNo = " + getWaybillNo() + ", " +
                "gmtCreate = " + getGmtCreate() + ", " +
                "gmtModified = " + getGmtModified() + ")";
    }
```

repository操作接口定义

```java
/**
 * @author dreamyao
 * @title
 * @date 2022/1/20 3:36 下午
 * @since 1.0.0
 */
public interface OpLogRepository extends R2dbcRepository<OpLog, Long> {
 
    Flux<OpLog> findAllByDescription(String desc, Pageable pageable);
 
    @Query("select * from ntocc_oplog where id=:id")
    Mono<OpLog> findOp(long id);
 
    @Modifying
    @Transactional
    @Query("update ntocc_oplog set description=:desc where id=:id")
    Mono<Integer> updateOpLogById(long id, String desc);
}
```

更多的使用方式请参考 官方文档 https://docs.spring.io/spring-data/r2dbc/docs/current/reference/html/#r2dbc.repositories 14章节 语法与 spring data JPA 几乎相同

## 数据源切换

通过在方法上添加 @SpecifyDataSource 注解来指定数据源，可以实现主从模式、指定数据源模式

## 多数据源配置

示例：

```properties
spring.datasource.r2dbc.demo.username = xxxxxx
spring.datasource.r2dbc.demo.password = xxxxxx
spring.datasource.r2dbc.demo.url = r2dbc:mysql://mysql-ntocc/ntocc_test?characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useSSL=false&allowMultiQueries=true&rewriteBatchedStatements=true&serverTimezone=GMT%2B8
```