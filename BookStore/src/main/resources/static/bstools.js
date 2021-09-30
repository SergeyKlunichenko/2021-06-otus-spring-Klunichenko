function loadOptions(select, api, defid){
        var selected = "";
        $.get(api).done(function(objects) {
            objects.forEach( function(object){
                selected = ""
                if(object.id === defid){
                    selected = "selected"
                }
                value =     '<option value="'+object.id+'" '+selected+'>'+object.name+'</option>';
                $(select).append(value)
            })

        })

}