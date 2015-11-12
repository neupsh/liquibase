package liquibase.actionlogic.core.mssql;

import liquibase.Scope;
import liquibase.action.ExecuteSqlAction;
import liquibase.action.core.RenameViewAction;
import liquibase.actionlogic.ActionResult;
import liquibase.actionlogic.DelegateResult;
import liquibase.actionlogic.core.RenameViewLogic;
import liquibase.database.Database;
import liquibase.database.core.mssql.MSSQLDatabase;
import liquibase.exception.ActionPerformException;

public class RenameViewLogicMSSQL extends RenameViewLogic {
    @Override
    protected Class<? extends Database> getRequiredDatabase() {
        return MSSQLDatabase.class;
    }

    @Override
    public ActionResult execute(RenameViewAction action, Scope scope) throws ActionPerformException {
        Database database = scope.getDatabase();
        return new DelegateResult(new ExecuteSqlAction(
                "exec sp_rename '"
                        + database.escapeObjectName(action.oldViewName)
                        + "', '"
                        + action.newViewName.name
                        + "'"));
    }
}
