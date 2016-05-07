import Model from 'ember-data/model';
import { hasMany, belongsTo } from 'ember-data/relationships';

export default Model.extend({
    league: belongsTo('league'),
    results: hasMany('game-result')
});
