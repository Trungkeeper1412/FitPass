package com.ks.fitpass.core.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Kbn {

    private int kbnId;
    private String kbnName;
    private int kbnKey;
    private String kbnValue;
}
