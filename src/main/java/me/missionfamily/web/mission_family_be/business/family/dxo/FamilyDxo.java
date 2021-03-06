package me.missionfamily.web.mission_family_be.business.family.dxo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.missionfamily.web.mission_family_be.business.account.model.AccountModel;
import me.missionfamily.web.mission_family_be.business.family.model.ConfirmModel;
import me.missionfamily.web.mission_family_be.business.family.model.FamilyModel;
import me.missionfamily.web.mission_family_be.business.family.model.InvitationModel;
import me.missionfamily.web.mission_family_be.business.family.model.KickMemberModel;
import me.missionfamily.web.mission_family_be.common.data_transfer.MissionRequest;
import me.missionfamily.web.mission_family_be.common.data_transfer.MissionResponse;
import me.missionfamily.web.mission_family_be.common.data_transfer.ResponseModel;
import me.missionfamily.web.mission_family_be.domain.Family;

import javax.validation.Valid;
import java.util.List;

@Builder
@NoArgsConstructor
public class FamilyDxo {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request implements MissionRequest {

        @Valid
        @JsonInclude(Include.NON_NULL)
        @JsonProperty("family")
        private FamilyModel family;

        @Valid
        @JsonProperty("account")
        private AccountModel account;

        @Valid
        @JsonInclude(Include.NON_NULL)
        private ConfirmModel confirm;

        @JsonInclude(Include.NON_NULL)
        @JsonProperty("target_member_id")
        private String memberId;

        @JsonInclude(Include.NON_NULL)
        @JsonProperty("kick")
        private KickMemberModel kickModel;

        @Override
        public AccountModel account() {
            return this.account;
        }
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response implements MissionResponse {

        @Valid
        @JsonProperty("result")
        private ResponseModel result;

        @JsonInclude(Include.NON_NULL)
        @JsonProperty("my_families")
        private List<FamilyModel> myFamilies;

        @JsonInclude(Include.NON_NULL)
        @JsonProperty("invitations")
        private List<InvitationModel> invitations;

        @Valid
        @JsonInclude(Include.NON_NULL)
        private ConfirmModel confirm;

        @Valid
        @JsonInclude(Include.NON_NULL)
        @JsonProperty("family")
        private FamilyModel family;

        @JsonInclude(Include.NON_NULL)
        @JsonProperty("member_to_be_invited")
        private String memberToBeInvited;

        @Override
        public int code() {
            return result.getCode();
        }
    }
}
