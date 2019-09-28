package com.four_friends.demetraserver.entity.food_params;

import com.four_friends.demetraserver.entity.Entity;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author gekko
 */
@DatabaseTable
public class Menu extends Entity {

    @DatabaseField(dataType = DataType.SERIALIZABLE)
    private Long[] menuPositionIds = new Long[]{};

    private List<MenuPosition> menuPositions = new ArrayList<>();
    //private Set<FoodCategory> foodCategories = new HashSet<>();

    public void addMenuPosition(MenuPosition menuPosition) {
        menuPositions.add(menuPosition);
        updateMenuPositionIds();
    }

    private void updateMenuPositionIds() {
        Long[] updatedRestarauntIds = this.menuPositions
                .stream()
                .filter(Objects::nonNull)
                .map((current_restaraunt) -> {
                    return current_restaraunt.getId();
                }).toArray(Long[]::new);
        setMenuPositionIds(updatedRestarauntIds);
    }

    public Long[] getMenuPositionIds() {
        return menuPositionIds;
    }

    public void setMenuPositionIds(Long[] menuPositionIds) {
        this.menuPositionIds = menuPositionIds;
    }

    public List<MenuPosition> getMenuPositions() {
        return menuPositions;
    }

    public Set<FoodCategory> getFoodCategory() {
        return this.menuPositions.stream().map((menuPosition) -> {
            return menuPosition.foodCategory;
        }).collect(Collectors.toSet());
    }

}
