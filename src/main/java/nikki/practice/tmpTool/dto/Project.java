package nikki.practice.tmpTool.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

//Lombok赋予的注解能力：👇
@Entity
//创建一张表
@Getter//读取数据
@Setter//设置修改数据
@NoArgsConstructor//不需要传入任何arguments的构造函数，参数是前端会给的
public class Project {
    @Id//Marks a field as the primary key of the entity. The primary key uniquely identifies each record in the database table.
    @GeneratedValue(strategy = GenerationType.IDENTITY)//Specifies how the primary key should be generated. It is often used in conjunction with the @Id annotation.
    private Long id;

    @NotBlank(message = "Project Name is required")
    private String projectName;

    @NotBlank(message = "Project Name is required")
    @Size(min = 4, max = 6, message = "Please use 4 to 6 characters")
    @Column(updatable = false, unique = true)
    private String projectId;

    @NotBlank(message = "Project Name is required")
    private String description;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date startDate;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date endDate;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date createAt;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date updateAt;

    @PrePersist//第一次放到系统之前要做的工作，仅此一次，相当于生产日期Specifies a method to be called before the entity is inserted (persisted) into the database. This is often used to set default values or perform some actions before the entity is saved
    //@PostPersist联动操作，比如下单是一个动作，下单成功与否是下一个动作，扣款成功与否也是联动动作:
    //
    //Specifies a method to be called after the entity is inserted (persisted) into the database. This can be used to perform additional actions that should happen after the entity has been successfully saved.
    protected void onCreate() {
        this.createAt = new Date();
    }

    @PreUpdate//Specifies a method to be called before an entity is updated in the database. This is often used to perform some actions or validations before an update operation
    //PostUpdate:联动操作
    //
    //Specifies a method to be called after an entity is updated in the database. This can be used to perform additional actions that should happen after the entity has been successfully updated
    protected void onUpdate() {
        this.updateAt = new Date();
    }


}