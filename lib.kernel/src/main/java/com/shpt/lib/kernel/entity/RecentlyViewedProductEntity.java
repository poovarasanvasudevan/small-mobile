package com.shpt.lib.kernel.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by poovarasanv on 22/6/17.
 *
 * @author poovarasanv
 * @project SHPT2
 * @on 22/6/17 at 1:43 PM
 */

@Entity(tableName = "recently_viewed_products")
public class RecentlyViewedProductEntity {

    @PrimaryKey(autoGenerate = true)
    int ID;
}
