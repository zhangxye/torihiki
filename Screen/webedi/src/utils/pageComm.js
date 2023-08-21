const pageCommMethods = {
    methods: {
    setDropDownList(x_class_cd, x_ret_list) {
        var _this = this;
        _this.$http.get('/api/common/dropDownList', {
            params: {
              class_cd: x_class_cd
            }
          })
          .then(function(response) {
            var errorcode = response.data.errorcode;
            if (errorcode == "200") {
              _this[x_ret_list] = response.data.data;
            } else if (errorcode == "500") {
              _this.$message.error(response.data.errormsg);
            }
          })
          .catch(function(error) {
            console.log(error);
          });
      },

      getLabelByValue(x_list, x_value){
        if( _.isUndefined(x_value) || x_value === '') return '';
        if(!_.isArray(x_list)) return '';
  
        return _.find(x_list,{value:x_value})['label'];
      },
    }

}

export default pageCommMethods