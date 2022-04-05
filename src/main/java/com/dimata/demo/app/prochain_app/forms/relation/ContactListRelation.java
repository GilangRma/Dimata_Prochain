package com.dimata.demo.app.prochain_app.forms.relation;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ContactListRelation {
    private String contactCode;
    private String salutation;
    private String personName;
    private String personLastname;
    private String motherName;
    private String compName;
}
