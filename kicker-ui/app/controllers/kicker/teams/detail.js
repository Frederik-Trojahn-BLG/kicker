import Ember from 'ember';

export default Ember.Controller.extend({

    filteredAccounts: Ember.computed.filter('model.accounts', function(account, index, array) {
        return account.get('name').indexOf(this.get('filterName')) !== -1;
    }),

    showError: false,
    errorMsg: '',

    actions: {
        updateList() {
            this.notifyPropertyChange('filteredAccounts');
        },

        hideError() {
            this.set('showError', false);
            this.set('errorMsg', '');
        },

        invite(id) {
            var me = this;
            console.log('Invite: ' + id + ' to ' + me.get('model.team.id'));
            var invite = me.get('store').createRecord('team-invitation', {
                team: me.get('model.team'),
                account: me.get('store').peekRecord('account', id)
            });

            invite.save().then(function(value) {
                // Success
                console.log(JSON.stringify(value));
            },
            function(error) {
                // Error
                console.log(JSON.stringify(error));

                me.set('showError', true);
                me.set('errorMsg', JSON.stringify(error));
            });
        }
    }

});
