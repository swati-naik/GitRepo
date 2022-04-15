package com.numpyninja.lms.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "tbl_lms_batch")
public class Batch {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "batch_id_generator")
    @SequenceGenerator(name = "batch_id_generator", sequenceName = "tbl_lms_batch_batch_id_seq", allocationSize = 1)
    Integer batchId;
    
    String batchName;
   
    String batchDescription;
    String batchStatus;

	@ManyToOne ( fetch = FetchType.LAZY)       // LMSPhase2 changes
    @JoinColumn ( name = "batch_program_id", nullable = false )  // LMSPhase2 changes
    //Long batchProgramId;                             
    private Program program;                         // LMSPhase2 changes  

    Integer batchNoOfClasses;
}
