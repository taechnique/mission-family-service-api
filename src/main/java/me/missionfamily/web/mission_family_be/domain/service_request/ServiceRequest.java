package me.missionfamily.web.mission_family_be.domain.service_request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.missionfamily.web.mission_family_be.common.service_enum.ServiceProperties;
import me.missionfamily.web.mission_family_be.domain.Family;
import me.missionfamily.web.mission_family_be.domain.MissionDomain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "MESSAGE_TYPE")
@Table(name = "mf_request")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class ServiceRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    @Enumerated(EnumType.STRING)
    private ServiceProperties typeClass;

    private Boolean isConfirmed;

    private LocalDateTime sentTime;

    /**
     * 메세지 셋팅
     * @param title
     * @param content
     * @param typeClass
     */
    protected final void setMessage(String title, String content, ServiceProperties typeClass ){
        this.title = title;
        this.content = content;
        this.typeClass = typeClass;
        this.isConfirmed = false;
        this.sentTime = LocalDateTime.now();
    }


    /**
     * 하위클래스 메세지 셋팅용
     * @param title
     * @param content
     * @param typeClass
     * @return
     */
    abstract ServiceRequest createRequest(MissionDomain messageSender, MissionDomain messageTarget, String title, String content, ServiceProperties typeClass);


    //==== Business Logic ====//

    public void confirmMessage(){
        this.isConfirmed = true;
    }
}
