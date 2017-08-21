package org.rutebanken.tiamat.rest.graphql.operations;

import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLList;
import graphql.schema.GraphQLNonNull;
import graphql.schema.GraphQLObjectType;
import org.rutebanken.tiamat.service.StopPlaceQuayDeleter;
import org.rutebanken.tiamat.service.StopPlaceQuayMerger;
import org.rutebanken.tiamat.service.StopPlaceQuayMover;
import org.rutebanken.tiamat.rest.graphql.scalars.DateScalar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static graphql.Scalars.GraphQLBoolean;
import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLArgument.newArgument;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static org.rutebanken.tiamat.rest.graphql.GraphQLNames.*;

@Component
public class StopPlaceOperationsBuilder {

    @Autowired
    private StopPlaceQuayMover stopPlaceQuayMover;

    @Autowired
    private StopPlaceQuayMerger stopPlaceQuayMerger;

    @Autowired
    private StopPlaceQuayDeleter stopPlaceQuayDeleter;

    @Autowired
    DateScalar dateScalar;

    public List<GraphQLFieldDefinition> getStopPlaceOperations(GraphQLObjectType stopPlaceObjectType) {
        List<GraphQLFieldDefinition> operations = new ArrayList<>();

        //Merge two StopPlaces
        operations.add(newFieldDefinition()
                .type(stopPlaceObjectType)
                .name(MERGE_STOP_PLACES)
                .description("Merges two StopPlaces by terminating 'from'-StopPlace, and copying quays/values into 'to'-StopPlace")
                .argument(newArgument().name(FROM_STOP_PLACE_ID).type(new GraphQLNonNull(GraphQLString)))
                .argument(newArgument().name(TO_STOP_PLACE_ID).type(new GraphQLNonNull(GraphQLString)))
                .argument(newArgument().name(FROM_VERSION_COMMENT).type(GraphQLString))
                .argument(newArgument().name(TO_VERSION_COMMENT).type(GraphQLString))
                .argument(newArgument().name(DRY_RUN).type(GraphQLBoolean).defaultValue(Boolean.FALSE).description("If set to true - the merge is not saved"))
                .dataFetcher(environment -> stopPlaceQuayMerger.mergeStopPlaces(environment.getArgument(FROM_STOP_PLACE_ID), environment.getArgument(TO_STOP_PLACE_ID), environment.getArgument(FROM_VERSION_COMMENT), environment.getArgument(TO_VERSION_COMMENT), environment.getArgument(DRY_RUN)))
                .build());

        //Merge two quays on a StopPlace
        operations.add(newFieldDefinition()
                .type(stopPlaceObjectType)
                .name(MERGE_QUAYS)
                .description("Merges two Quays on a StopPlace.")
                .argument(newArgument().name(STOP_PLACE_ID).type(new GraphQLNonNull(GraphQLString)))
                .argument(newArgument().name(FROM_QUAY_ID).type(new GraphQLNonNull(GraphQLString)))
                .argument(newArgument().name(TO_QUAY_ID).type(new GraphQLNonNull(GraphQLString)))
                .argument(newArgument().name(VERSION_COMMENT).type(GraphQLString))
                .argument(newArgument().name(DRY_RUN).type(GraphQLBoolean).defaultValue(Boolean.FALSE).description("If set to true - the merge is not saved"))
                .dataFetcher(environment -> stopPlaceQuayMerger.mergeQuays(environment.getArgument(STOP_PLACE_ID), environment.getArgument(FROM_QUAY_ID), environment.getArgument(TO_QUAY_ID), environment.getArgument(VERSION_COMMENT), environment.getArgument(DRY_RUN)))
                .build());

        operations.add(newFieldDefinition()
                .type(stopPlaceObjectType)
                .name(MOVE_QUAYS_TO_STOP)
                .description("Moves one or more quays to a new or existing stop place. Returns the destination stop place.")
                .argument(newArgument()
                        .name(QUAY_IDS)
                        .description("A list of Quay IDs to move to the destination stop place. Quays must belong to the same stop place.")
                        .type(new GraphQLList(new GraphQLNonNull(GraphQLString))))
                .argument(newArgument()
                        .name(TO_STOP_PLACE_ID)
                        .description("The target stop place ID to move quays to. If not specified, a new stop place will be created.")
                        .type(GraphQLString))
                .argument(newArgument().name(FROM_VERSION_COMMENT).type(GraphQLString))
                .argument(newArgument().name(TO_VERSION_COMMENT).type(GraphQLString))
                .dataFetcher(environment -> stopPlaceQuayMover.moveQuays(environment.getArgument(QUAY_IDS), environment.getArgument(TO_STOP_PLACE_ID), environment.getArgument(FROM_VERSION_COMMENT), environment.getArgument(TO_VERSION_COMMENT)))
                .build());

        //Delete StopPlace
        operations.add(newFieldDefinition()
                .type(GraphQLBoolean)
                .name(DELETE_STOP_PLACE)
                .description("!!! Deletes all versions of StopPlace from database - use with caution !!!")
                .argument(newArgument().name(STOP_PLACE_ID).type(new GraphQLNonNull(GraphQLString)))
                .dataFetcher(environment -> stopPlaceQuayDeleter.deleteStopPlace(environment.getArgument(STOP_PLACE_ID)))
                .build());

        //Terminate StopPlace
        operations.add(newFieldDefinition()
                .type(stopPlaceObjectType)
                .name(TERMINATE_STOP_PLACE)
                .description("StopPlace will no longer be active after the given date.")
                .argument(newArgument().name(STOP_PLACE_ID).type(new GraphQLNonNull(GraphQLString)))
                .argument(newArgument().name(VALID_BETWEEN_TO_DATE).type(new GraphQLNonNull(dateScalar.getGraphQLDateScalar())))
                .argument(newArgument().name(VERSION_COMMENT).type(GraphQLString))
                .dataFetcher(environment -> stopPlaceQuayDeleter.terminateStopPlace(environment.getArgument(STOP_PLACE_ID), environment.getArgument(VALID_BETWEEN_TO_DATE), environment.getArgument(VERSION_COMMENT)))
                .build());


        //Reopen StopPlace
        operations.add(newFieldDefinition()
                .type(stopPlaceObjectType)
                .name(REOPEN_STOP_PLACE)
                .description("StopPlace will no longer be active after the given date.")
                .argument(newArgument().name(STOP_PLACE_ID).type(new GraphQLNonNull(GraphQLString)))
                .argument(newArgument().name(VERSION_COMMENT).type(GraphQLString))
                .dataFetcher(environment -> stopPlaceQuayDeleter.reopenStopPlace(environment.getArgument(STOP_PLACE_ID), environment.getArgument(VERSION_COMMENT)))
                .build());

        //Delete Quay from StopPlace
        operations.add(newFieldDefinition()
                .type(stopPlaceObjectType)
                .name(DELETE_QUAY_FROM_STOP_PLACE)
                .description("Removes quay from StopPlace")
                .argument(newArgument().name(STOP_PLACE_ID).type(new GraphQLNonNull(GraphQLString)))
                .argument(newArgument().name(QUAY_ID).type(new GraphQLNonNull(GraphQLString)))
                .argument(newArgument().name(VERSION_COMMENT).type(GraphQLString))
                .dataFetcher(environment -> stopPlaceQuayDeleter.deleteQuay(environment.getArgument(STOP_PLACE_ID), environment.getArgument(QUAY_ID), environment.getArgument(VERSION_COMMENT)))
                .build());

        return operations;
    }

}