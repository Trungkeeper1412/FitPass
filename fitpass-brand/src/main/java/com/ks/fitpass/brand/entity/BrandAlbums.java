package com.ks.fitpass.brand.entity;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BrandAlbums {

    private int albumId;

    private int brandId;

    private String photoUrl;

    private String description;
}
