import Ember from 'ember';

export default Ember.Controller.extend({

    filteredAccounts: Ember.computed.filter('model.accounts', function(account, index, array) {
        let filterName = this.getProperties('filterName');
        console.log('Filter: ' + filterName);
        return !account.get('name').indexOf(this.get('filterName')) > -1;
    }),

    actions: {
        updateList() {
            filteredAccounts.notifyPropertyChange();
        }
    }

});
