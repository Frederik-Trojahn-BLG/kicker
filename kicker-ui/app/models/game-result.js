import Model from 'ember-data/model';
import attr from 'ember-data/attr';
import { belongsTo } from 'ember-data/relationships';

export default Model.extend({
    team: belongsTo('team'),
    game: belongsTo('game'),
    score: attr('integer')
});
