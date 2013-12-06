$('#old-contrast').affix(
        {
            offset: {
                top: function() {
                    var header = $('#header').height()
                    var setUpForm = $('#set-up-form').height()
                    return (this.top = header + setUpForm + 135)
                }
            }
        })
