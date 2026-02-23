package com.example.text_llm_agent

import org.springframework.stereotype.Component
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.SQLData

@Component
class SQLUtil {
    private val url = "jdbc:sqlite:text.db"

    fun initDb() {
        DriverManager.getConnection(url).use { conn: Connection ->
            conn.createStatement().use { stmt ->
                stmt.executeUpdate(
                    """
                    CREATE TABLE IF NOT EXISTS indexKey (
                        newIndex TEXT
                    )
                    """.trimIndent()
                )

                // Ensure a row exists (so UPDATE works later)
                stmt.executeUpdate(
                    """
                    INSERT INTO indexKey (newIndex)
                    SELECT '0'
                    WHERE NOT EXISTS (SELECT 1 FROM indexKey)
                    """.trimIndent()
                )
            }
        }
    }

    fun queryIndex(): String {
        DriverManager.getConnection(url).use { conn: Connection ->
            conn.createStatement().use { stmt ->
                val rs: ResultSet = stmt.executeQuery("SELECT newIndex FROM indexKey LIMIT 1")
                return if (rs.next()) {
                    rs.getString("newIndex")
                } else {
                    ""
                }
            }
        }
    }

    fun addIndex(newIndex: String) {
        DriverManager.getConnection(url).use { conn: Connection ->
            conn.prepareStatement("UPDATE indexKey SET newIndex = ?").use { stmt ->
                stmt.setString(1, newIndex)
                stmt.executeUpdate()
            }
        }
    }
}