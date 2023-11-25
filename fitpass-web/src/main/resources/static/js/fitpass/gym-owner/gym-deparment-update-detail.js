$("#submitForm").click(function() {
    var formData = {
        departmentId: $("#departmentId").val(),
        departmentAddress: $("#address").val(),
        departmentContactNumber: $("#phone").val(),
        departmentDescription: $("#description").val(),
        capacity: $("#capacity").val(),
        area: $("#area").val(),
        city: $("#provinceDropdown").val(),
        mondayOpenTime: $("#mondayOpenTime").val(),
        mondayCloseTime: $("#mondayCloseTime").val(),
        tuesdayOpenTime: $("#tuesdayOpenTime").val(),
        tuesdayCloseTime: $("#tuesdayCloseTime").val(),
        wednesdayOpenTime: $("#wednesdayOpenTime").val(),
        wednesdayCloseTime: $("#wednesdayCloseTime").val(),
        thursdayOpenTime: $("#thursdayOpenTime").val(),
        thursdayCloseTime: $("#thursdayCloseTime").val(),
        fridayOpenTime: $("#fridayOpenTime").val(),
        fridayCloseTime: $("#fridayCloseTime").val(),
        saturdayOpenTime: $("#saturdayOpenTime").val(),
        saturdayCloseTime: $("#saturdayCloseTime").val(),
        sundayOpenTime: $("#sundayOpenTime").val(),
        sundayCloseTime: $("#sundayCloseTime").val(),
        listSelectedFlexGymPlanId: $("#selectedFlexibleId").val(),
        listSelectedFixedGymPlanId: $("#selectedFixedId").val(),
        listSelectedAmenitiesId: $("#selectedAmenitieId").val(),
        listSelectedFeaturesId: $("#selectedFeatureId").val(),
        longitude: $("#longitude").val(),
        latitude: $("#latitude").val()
    };

    $.ajax({
        type: "POST",
        url: "/gym-owner/department/update-details",
        data: JSON.stringify(formData),
        contentType: "application/json",
        success: function(response) {
            console.log(response);
        },
        error: function(error) {
            console.log(error);
        }
    });
});
