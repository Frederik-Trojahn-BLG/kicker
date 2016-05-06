import Model from 'ember-data/model';
import { belongsTo } from 'ember-data/relationships';

export default Model.extend({
    team: belongsTo('team'),
    account: belongsTo('account')
});
