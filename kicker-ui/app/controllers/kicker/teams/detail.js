import Ember from 'ember';

export default Ember.Controller.extend({

    filteredAccounts: Ember.computed.filter('model.accounts', function(account, index, array) {
        return account.get('name').indexOf(this.get('filterName')) !== -1;
    }),

    actions: {
        updateList() {
            this.notifyPropertyChange('filteredAccounts');
        },

        invite(id) {
            console.log('Invite: ' + id);
        }
    }

});
